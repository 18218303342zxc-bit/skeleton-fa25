//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }
}


public List<String> unharmoniousTexts(Sniffer sniffer,int M) {
    ArrayList<String> getallmessages = new ArrayList<>();
    for(Timer timer = new Timer(); timer.hours() < 24;) {
        getallmessages.add(sniffer.getNextmessages());
    }
    Comparator<String> cmptr = new HarmoniousnessComparator();
    Collections.sort(getallmessages,cmptr,Collections.reverseOrder());
  return getallmessages.subList(0,M);
}