package de.kuratan.steamkreations.block.heater;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.kuratan.steamkreations.container.ContainerHeater;
import de.kuratan.steamkreations.crafting.HeaterManager;
import de.kuratan.steamkreations.crafting.HeaterRecipe;
import de.kuratan.steamkreations.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * TileEntity representing a basic heater for making chocolate.
 */
public class TileEntityHeater extends TileFluidHandler implements ISidedInventory, IFluidHandler {

    public enum TYPES {
        NORMAL(4000, 20), REINFORCED(8000, 40), CREATIVE(-1, 40);

        private int steamCapacity;
        private int steamPerTick;

        TYPES(int steamCapacity, int steamPerTick) {
            this.steamCapacity = steamCapacity;
            this.steamPerTick = steamPerTick;
        }

        /**
         * @return Internal tank capacity in milibuckets.
         */
        public int getSteamCapacity() {
            return steamCapacity;
        }

        /**
         * @return Used steam in milibuckets per tick.
         */
        public int getSteamPerTick() {
            return steamPerTick;
        }
    }

    protected TYPES type;
    protected ItemStack[] inventory;
    protected int cookTime;
    protected HeaterRecipe recipe;

    public TileEntityHeater() {
        super();
    }

    /**
     * Updates internal paramaters using the values from the provided type.
     *
     * @param type
     */
    public void setType(TYPES type) {
        this.type = type;
        this.tank.setCapacity(type.getSteamCapacity());
        this.tank.setFluid(new FluidStack(FluidRegistry.WATER, 1000));
        this.inventory = new ItemStack[ContainerHeater.SLOTS.values().length];
        this.cookTime = -1;
        this.recipe = null;
    }

    public TYPES getType() {
        return type;
    }

    /**
     * Serializes important data into NBT. Slots of the internal inventory are only saved if not empty.
     *
     * @param tag
     */
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("type", this.getType().ordinal());
        tag.setInteger("cookTime", this.cookTime);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (inventory[i] != null) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("slot", (byte) i);
                inventory[i].writeToNBT(slot);
                tagList.appendTag(slot);
            }
        }
        tag.setTag(this.getInventoryName(), tagList);
    }

    /**
     * Restores serialized data from NBT.
     *
     * @param tag
     */
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setType(TYPES.values()[tag.getInteger("type")]);
        this.cookTime = tag.getInteger("cookTime");
        NBTTagList tagList = tag.getTagList(this.getInventoryName(), 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound slot = tagList.getCompoundTagAt(i);
            byte index = slot.getByte("slot");
            if (index >= 0 && index < getSizeInventory()) {
                inventory[index] = ItemStack.loadItemStackFromNBT(slot);
            }
        }
        this.calculateSlotData(ContainerHeater.SLOTS.INPUT.getId());
    }

    /**
     * Do not allow output of fluid.
     *
     * @param from
     * @param fluid
     * @return false
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    /**
     * Allow steam input from the bottom.
     *
     * @param from
     * @param fluid
     * @return
     */
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from.equals(ForgeDirection.DOWN) && super.canFill(from, fluid);
    }

    /**
     * Bottom is fluid only, all other sides can access all slots.
     *
     * @param iside
     * @return
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int iside) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        switch (side) {
            case DOWN:
                return new int[0];
            default:
                if (this.inventory.length == 2) {
                    return new int[]{0, 1};
                }
                return new int[]{0, 1, 2, 3};
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int iside) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        return side != ForgeDirection.DOWN && isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int iside) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        return side != ForgeDirection.DOWN;
    }


    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < getSizeInventory()) {
            return this.inventory[slot];
        }
        return null;
    }

    /**
     * Decrements slot using setInventorySlotContents.
     *
     * @param slot
     * @param decrement
     * @return
     */
    @Override
    public ItemStack decrStackSize(int slot, int decrement) {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrement) {
                setInventorySlotContents(slot, null);
            } else {
                itemStack = itemStack.splitStack(decrement);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return itemStack;
    }

    /**
     * @param slot
     * @return
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    /**
     * Sets contents of slot.
     *
     * @param slot
     * @param itemStack
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (slot < getSizeInventory()) {
            this.inventory[slot] = itemStack;
            if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
                itemStack.stackSize = getInventoryStackLimit();
            }
            calculateSlotData(slot);
        }
    }

    @Override
    public String getInventoryName() {
        return "container.heater";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityPlayer.getDistanceSq(
                (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return false;
    }

    public boolean active() {
        return this.recipe != null && this.cookTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int width) {
        return (this.recipe.getDuration() - this.cookTime) * width / this.recipe.getDuration();
    }

    /**
     * Consume steam and cook items if there cooking time has finished.
     */
    @Override
    public void updateEntity() {
        if (tank.getFluidAmount() >= type.getSteamPerTick() || this.type == TYPES.CREATIVE) {
            tank.drain(type.getSteamPerTick(), true);

            if (this.recipe != null) {
                if (cookTime == 0) {
                    recipe.handleInventory(this);
                    this.recipe = null;
                } else if (this.cookTime > 0) {
                    this.cookTime--;
                }
            }

            this.markDirty();
        }
    }

    public void calculateSlotData(int slotNumber) {
        if (slotNumber == ContainerHeater.SLOTS.OUTPUT.getId()) {
            return;
        }
        recipe = HeaterManager.getRecipe(inventory);
        if (recipe != null) {
            this.cookTime = recipe.getDuration();
        } else {
            this.cookTime = -1;
        }
    }
}
