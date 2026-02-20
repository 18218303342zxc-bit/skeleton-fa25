public class Stack {
    private Linkedlist stack;
    public Stack(){
        stack = new Linkedlist();
    }
    public void push(int x){
        stack.add_last(x);
    }
    public int pop(){
        return stack.remove_last();
    }
    public int size(){
        return stack.size();
    }
    public boolean is_empty(){
        if (this.size() == 0){
            return true;
        } else {
            return false;
        }
    }
    public int sum(){
      int S = 0;
      while (! this.is_empty()){
          S += this.pop();
      }
      return S;
    }
    public static class Intnode{
        public int  item;
        public Intnode next;
        public Intnode prev;
        public Intnode (int x,Intnode y,Intnode z) {
            item = x;
            next = z;
            prev = y;
        }
    }
    public static class LinkedList{
        private Intnode sentine;
        private Intnode last;
        private int size;

        public LinkedList (){
            sentine = new Intnode(-1,null,null);
            last = new Intnode(-1,null,null);
            sentine.next = last;
            last.prev = sentine;
            size = 0;

        }
        public void add_last (int x){
           Intnode a = new Intnode(x,last.prev,last);
           last.prev.next = a;
           last.prev = a;


            size += 1;
        }
        public int get_last(){
            if(size() != 0){
                return last.prev.item;
            } else {
                return -1;
            }

        }
        public int remove_last(){
            if(last.prev != sentine){
                last.prev.prev.next = last;
                int result = last.prev.item;
                last.prev = last.prev.prev;
                size -= 1;
                return result;

            } else {
                return -1;
            }
        }
        public int size (){
            return size;
        }
    }

}
