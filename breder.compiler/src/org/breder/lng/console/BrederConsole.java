package org.breder.lng.console;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.breder.lng.exception.ParserException;
import org.breder.lng.lexer.BrederLexer;
import org.breder.lng.node.command.CommandNode;
import org.breder.lng.node.command.NullNode;
import org.breder.lng.parser.BrederParser;
import org.breder.lng.util.StreamOpcodeInputStream;
import org.breder.lng.util.StreamOpcodeOutputStream;
import org.breder.lng.vm.BrederVM;

public class BrederConsole {

	public static void main(String[] args) throws IOException {
		BrederVM vm = new BrederVM();
		BrederParser parser = new BrederParser(new BrederLexer(System.in));
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		StreamOpcodeOutputStream output = new StreamOpcodeOutputStream(bytes);
		for (;;) {
			try {
				CommandNode command = parser.readCommand();
				if (command != NullNode.INSTANCE) {
					command.build(output);
					output.opControlHalf();
					StreamOpcodeInputStream input = new StreamOpcodeInputStream(
							new ByteArrayInputStream(bytes.toByteArray()));
					Object object = vm.execute(input);
					System.out.println(object);
				}
			} catch (ParserException e) {
				System.out.println("parser exception");
				parser.reset();
			}
			bytes.reset();
		}
	}

}
