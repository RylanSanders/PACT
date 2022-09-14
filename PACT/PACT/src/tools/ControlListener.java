package tools;

import java.awt.Component;
import java.util.EventListener;

public interface ControlListener extends EventListener{
public void addListenerTo(Component comp);
public void removeListenerFrom(Component comp);
public void setCommand(Command cmd);
public Command getCommand();
}
