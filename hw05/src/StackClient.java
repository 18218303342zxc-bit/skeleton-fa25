public class StackClient {
   public static Stack flipped(Stack s){
       Stack result = new Stack();
       while(! s.is_empty()){
           result.push(s.pop());
       }
       return result;
   }

}
