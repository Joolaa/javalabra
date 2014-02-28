package fi.javalabra.logic;

/**
 * This class wraps up all the blocks to a linked structure
 * @author jola
 */
public class Blocks {
    
    private BlockNode first;
    private BlockNode last;
    
    private BlockNode current;
    
    private int size;
    
    private boolean locked;
    
    /**
     * Node of a linked list with a Block as a payload
     */
    private class BlockNode {
        
        private BlockNode next;
        private BlockNode prev;
        
        private Block payload;
        
        private BlockNode(BlockNode next, BlockNode prev, Block payload) {
            
            this.next = next;
            this.prev = prev;
            
            this.payload = payload;
            
        }
    }
    
    /**
     * Constructor for Blocks-instance
     */
    public Blocks() {
        
        this.first = null;
        this.last = null;
        
        this.current = null;
        
        this.locked = false;
        
        this.size = 0;
    }
    
    /**
     * Insert a block to the list
     * @param block the block to be inserted
     */
    public void insert(Block block) {
        
        if(first == null) {
            
            first = new BlockNode(null, null, block);
            last = first;
            
        } else if(first == last) {
            
            first = new BlockNode(last, null, block);
            last.prev = first;
        } else {
            
            BlockNode second = first;
            
            first = new BlockNode(second, null, block);
            second.prev = first;
        }
        
        size++;
    }
    
    /**
     * Delete the block which was last time returned by getNext() or getPrev()
     */
    public void deleteCurrent() {
        
        delete(current);
    }
    
    private void delete(BlockNode node) {
        
        if(node == null || first == null || last == null) {
            
            return;
            
        } else if(node == first && node == last) {
            
            first = null;
            last = null;
            
        } else if(node == first) {

            first = first.next;
            first.prev = null;
        } else if(node == last) {

            last = last.prev;
            last.next = null;
        } else {

            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        
        size--;
    }
    
    /**
     * Get the first block on the list
     * @return the first block on the list
     */
    public Block getFirst() {
        
        if(first == null)
            return null;
        
        return first.payload;
    }
    
    /**
     * Get the last block on the list
     * @return last block on the list
     */
    public Block getLast() {
        
        if(last == null)
            return null;
        
        return last.payload;
    }
    
    /**
     * Get the next block on the list. If not called before, or rewind() has
     * been called since the last call or end of the list was reached,
     * returns the first element on the list.
     * @return the next element on the list, or null after the last element,
     * but first after that
     */
    public Block getNext() {
        if(first == null)
            return null;
        
        if(current == null) {
            
            current = first;
        } else if(current.next == null) {
            current = null;
            return null;
        } else {
            current = current.next;
        }
        
        return current.payload;
    }
    
    /**
     * Get the previous block on the list. If not called before, or rewind() has
     * been called since the last call or the start of the list was reached,
     * returns the last element on the list.
     * @return the previous element on the list,
     * or null after the first element, but last after that
     */
    public Block getPrev() {
        if(last == null)
            return null;
        
        if(current == null) {
            
            current = last;
        } else if(current.prev == null) {
            current = null;
            return null;
        } else {
            current = current.prev;
        }
        
        return current.payload;
    }
    
    /**
     * Rewind the list pointer to null. This will cause the next getNext() call
     * to return the first element and the next getPrev() call to return the
     * last element.
     */
    public void rewind() {
        
        current = null;
    }
    
    /**
     * Get the length of the list
     * @return the length of the list
     */
    public int size() {
        
        /*
        BlockNode point = first;
        
        int counter = 0;
        
        while(point != null) {
            counter++;
            point = point.next;
        }
        
        return counter;
        */
        
        return size;
    }
    
    /**
     * Get the lock state if you wish to check if the blocks-instance
     * is already being used by other concurrent routine
     * @return the lock state, true if locked
     */
    public boolean getLocked() {
        
        return locked;
    }
    
    /**
     * Set lock state to true
     */
    public void lock() {
        
        locked = true;
    }
    
    /**
     * Set lock state to false
     */
    public void unlock() {
        
        locked = false;
    }
    
    /**
     *  Nullifies the block list and sets everything to default.
     */
    public void nullifyBlockList() {
        
        last = null;
        first = null;
        size = 0;
        current = null;
        
        locked = false;
    }
    
}
