
package breder.compiler.node.standart;

public enum BAccess {

	PUBLIC {

		public String getName() {
			return "public";
		}
	},
	PROTECTED {

		public String getName() {
			return "protected";
		}
	},
	PRIVATE {

		public String getName() {
			return "private";
		}
	};

}
