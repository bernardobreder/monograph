package breder.risk.model;

import javax.swing.ImageIcon;

import breder.risk.resource.BrederRiskResource;

import com.sun.java.swing.plaf.motif.resources.motif;

public enum RiskEnum {

	CRITICAL {
		@Override
		public String toString() {
			return "Catastrofico";
		}

		@Override
		public ImageIcon getIcon() {
			return BrederRiskResource.getInstance().getCriticalImage();
		}
	},
	MODERAL {
		@Override
		public String toString() {
			return "Moderado";
		}

		@Override
		public ImageIcon getIcon() {
			return BrederRiskResource.getInstance().getModeralImage();
		}
	},
	NORMAL {
		@Override
		public String toString() {
			return "Normal";
		}

		@Override
		public ImageIcon getIcon() {
			return BrederRiskResource.getInstance().getNormalImage();
		}
	},
	LIGHT {
		@Override
		public String toString() {
			return "Leve";
		}

		@Override
		public ImageIcon getIcon() {
			return BrederRiskResource.getInstance().getLightImage();
		}
	},
	ZERO {
		@Override
		public String toString() {
			return "Insignificante";
		}

		@Override
		public ImageIcon getIcon() {
			return BrederRiskResource.getInstance().getZeroImage();
		}
	};

	public abstract ImageIcon getIcon();

	public static RiskEnum getEnum(String toString) {
		if (CRITICAL.toString().equals(toString)) {
			return CRITICAL;
		} else if (MODERAL.toString().equals(toString)) {
			return MODERAL;
		} else if (NORMAL.toString().equals(toString)) {
			return NORMAL;
		} else if (LIGHT.toString().equals(toString)) {
			return LIGHT;
		} else if (ZERO.toString().equals(toString)) {
			return ZERO;
		} else {
			return null;
		}
	}

}
