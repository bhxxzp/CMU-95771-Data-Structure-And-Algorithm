package ApproximateTSP;

public class minHeap {
	
	    private Vertex[] Heap; 
	    private int size; 
	    private int maxsize; 


	    public minHeap(int maxsize) 
	    { 
	       this.maxsize = maxsize;
	       Heap = new Vertex[maxsize+1];
	    } 
	    
	    public boolean contains (int v) {
	    	  for (int i = 1; i <= size; i++) {
	              if (Heap[i].getIndex() == v) {
	                  return true;
	              }
	          }
	          return false;
	    }
	    
	    public boolean isEmpty() {
	    		return size==0;
	    }
	
	    
	    public int getSize() {
	    		return size;
	    }
	    
	    public int getMaxsize() {
    		return maxsize;
    }
	    
	    public void minHeapify(int i) {
	    		while(i >1 && (Heap[i].compare(Heap[i/2]) == -1)) {
	    			swap(i, i/2);
	    			i = i/2;
	    		}
	    }
	    
	  
	    
	    private void Heapilify(int k) {

	        while (2 * k <= size) {
	            int j = 2 * k;
	            if (j < size && (Heap[j+1].compare(Heap[j]) == -1)) {
	                j++;
	            }
	            if (Heap[k].compare(Heap[j]) == -1) {
	                break;
	            }
	           swap(k, j);
	            k = j;
	        }

	    }
	    
	    public int deleteMin() {
    		Vertex noo = Heap[1];
    		swap(1, size);
    		Heap[size] = null;
    		size-- ;
    		Heapilify(1);
		return noo.getIndex();
	    		}
	    
	    
	    public void newState(int index, double num) {
	    		for(int i = 1; i <= size; i++) {
	    			if(Heap[i].getIndex() == index) {
	    				Heap[i].setDis(num);
	    				minHeapify(i);
	    				break;
	    			}
	    		}
	    }
	   
	  
	    // Function to swap two nodes of the heap 
	    private void swap(int fpos, int spos) 
	    { 
	        Vertex tmp; 
	        tmp = Heap[fpos]; 
	        Heap[fpos] = Heap[spos]; 
	        Heap[spos] = tmp; 
	    } 
	  

	  
	    // Function to insert a node into the heap 
	    public void insert(Vertex ele) 
	    { 
	    		size ++;
	    		int i = 1;
	    		while(Heap[i] != null) {
	    			i++;
	    		}
	    		Heap[i] = ele;
	    		minHeapify(i);
	        
	    } 
	  
	   
	    

	} 