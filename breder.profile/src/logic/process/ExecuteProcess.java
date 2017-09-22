package logic.process;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import breder.compiler.compiler.CompilerConstant;

import logic.Binary;
import logic.struct.JPClass;

public class ExecuteProcess {

	public final Binary binary;

	public final List<InstructionEntity> steps = new ArrayList<InstructionEntity>();

	public String binarypath;

	public static final int INSTRUCTION_LPARAM_INDEX = 0;

	public static final int INSTRUCTION_RPARAM_INDEX = 9;

	public static final String NUMBER_CLASS = CompilerConstant.NUMBER_CLASS;

	public static final String BOOLEAN_CLASS = CompilerConstant.BOOLEAN_CLASS;

	public static final String STRING_CLASS = CompilerConstant.STRING_CLASS;

	public static final String BINARY_PATH = "../breder.compiler/binary.b";

	public static final String SOURCE_PATH = "../breder.compiler/tst/"
			+ "breder/test/Test.breder";

	private Map<String, Integer> ids = new HashMap<String, Integer>();

	public ExecuteProcess(Binary binary) {
		this(binary, BINARY_PATH);
	}

	public ExecuteProcess(Binary binary, String binarypath) {
		super();
		this.binary = binary;
		this.binarypath = binarypath;
	}

	public void start() throws IOException {
		Process process = new ProcessBuilder("../breder_vm/Debug/breder",
				this.binarypath, "-lp", "../breder.native.lang/Debug", "-lp",
				"../breder.native.util/Debug", "-lp",
				"../breder.native.gui/Debug").start();
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(
				process.getInputStream()));
		String line = reader.readLine();
		while (line != null) {
			this.process(reader, line);
			line = reader.readLine();
		}
	}

	private void process(LineNumberReader reader, String line)
			throws IOException {
		if (line.length() > 0) {
			if (line.startsWith("[info]")) {
				this.processInfo(line);
			} else if (line.charAt(INSTRUCTION_LPARAM_INDEX) == '['
					&& line.charAt(INSTRUCTION_RPARAM_INDEX) == ']') {
				this.processInstruction(reader, line);
			}
		}
	}

	private void processInfo(String line) {
	}

	private void processInstruction(LineNumberReader reader, String line)
			throws IOException {
		InstructionEntity entity = new InstructionEntity();
		entity.setIndex(new Integer(line.substring(
				INSTRUCTION_LPARAM_INDEX + 1, INSTRUCTION_RPARAM_INDEX).trim()));
		line = reader.readLine().trim();
		if (!line.startsWith("{")) {
			if (line.charAt(INSTRUCTION_LPARAM_INDEX) == '['
					&& line.charAt(INSTRUCTION_RPARAM_INDEX) == ']') {
				this.processInstruction(reader, line);
			}
			return;
		}
		if (line.length() > 2) {
			String[] stacks = line.substring(1, line.length() - 1).split(",");
			for (String stack : stacks) {
				if (stack.equals("0")) {
					entity.addStack(new NullStackEntity());
				} else {
					String[] splits = stack.split(" ");
					JPClass clazz = null;
					for (JPClass c : binary.getVm().getClasses()) {
						if (c.getName().equals(splits[0])) {
							clazz = c;
							break;
						}
					}
					if (clazz == null) {
						throw new RuntimeException(
								"not found the class with name : " + splits[0]);
					}
					String idStr = splits[1];
					Integer id = ids.get(idStr);
					if (id == null) {
						id = ids.size();
						ids.put(idStr, id);
					}
					if (splits.length > 2) {
						Object value;
						String name = clazz.getName();
						if (name.equals(NUMBER_CLASS)
								|| name.equals(CompilerConstant.INTEGER_CLASS)
								|| name.equals(CompilerConstant.NATURAL_CLASS)
								|| name.equals(CompilerConstant.INDEX_CLASS)) {
							value = new Double(splits[3]);
						} else if (name.equals(BOOLEAN_CLASS)) {
							if (splits[3].equals("true")) {
								value = true;
							} else {
								value = false;
							}
						} else {
							throw new RuntimeException("not implemented yet");
						}
						entity.addStack(new ValuableStackEntity(clazz, id,
								value));
						continue;
					}
					entity.addStack(new BasicStackEntity(clazz, id));
				}
			}
		}
		line = reader.readLine();
		if (false && line != null) {
			line = line.trim();
			String[] methods = line.substring(1, line.length() - 1).split(",");
			for (String method : methods) {
				entity.addStackTrace(method);
			}
		}
		this.steps.add(entity);
	}

	public List<InstructionEntity> getSteps() {
		return steps;
	}
}
