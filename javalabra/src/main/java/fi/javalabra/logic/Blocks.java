package fi.javalabra.logic;

public class Blocks {
    
    private BlockNode first;
    private BlockNode last;
    
    private BlockNode current;
    
    private boolean locked;
    
    private int size;
    
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
    
    public Blocks() {
        
        this.first = null;
        this.last = null;
        
        this.current = null;
        
        this.locked = false;
        
        this.size = 0;
    }
    
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
    
    public Block getFirst() {
        
        return first.payload;
    }
    
    public Block getLast() {
        
        return last.payload;
    }
    
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
    
    public void rewind() {
        
        current = null;
    }
    
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
    
    public boolean getLocked() {
        return locked;
    }
    
    public void lock() {
        locked = true;
    }
    
    public void unlock() {
        locked = false;
    }
    
}
