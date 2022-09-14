package tools;

public enum Tools {
	pencil, eraser, fill, line, shape, eyedropper, undo, redo, select;

	public static int toolIDInt(Tools tool) {
		switch(tool) {
		case pencil: return 0;
		case eraser: return 1;
		case fill: return 2;
		case line: return 3;
		case shape: return 4;
		case select: return 5;
		case eyedropper: return 6;
		
		default: return -1;
		}
	}
	
	public static String toolToString(Tools tool) {
		switch(tool) {
		case pencil: return "Pencil";
		case eraser: return "Eraser";
		case fill: return "Fill";
		case line: return "Line";
		case shape: return "Shape";
		case eyedropper: return "Eye Dropper";
		case undo: return "Undo";
		case redo: return "Redo";
		case select: return "Select";
		default: return "Unknown";
		}
	}
}
