package tabby.expander.processor;


public class ProcessorFactory {

    public static Processor newInstance(String name){
        switch (name){
            case "JavaGadget":
                return new JavaGadgetProcessor();
            default:
                return new CommonProcessor();
        }
    }
}
