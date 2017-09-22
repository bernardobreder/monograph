package breder.processor.model.breder;

import static breder.processor.model.breder.ArchModel.SPACE;

import java.util.ArrayList;
import java.util.List;

import breder.processor.model.GroupComponent;
import breder.processor.model.IArchModel;
import breder.processor.model.IObject;
import breder.processor.model.Line;
import breder.processor.model.Point;

public class KernelModel implements IArchModel {

	private List<IObject> objects = new ArrayList<IObject>();

	private String[] registers = new String[] { "OSC", "MSC", "PC", "ACC", "IR", "OSTC", "MSTC", "PTC", "THROW", "-1", "0", "+1", "+2" };

	private final int REGISTER_CONST = 4;

	private Shift shift;

	private Alu alu;

	private Amux amux;

	private final int INIT_Y = 2 * SPACE;

	private final int INIT_X = 2 * SPACE;

	public KernelModel() {
		List<IObject> llist = new ArrayList<IObject>();
		llist.add(this.buildComponents());
		llist.add(this.buildLines());
		this.objects.add(new GroupComponent(INIT_X, INIT_Y, llist.toArray(new IObject[0])));
	}

	private IObject buildLines() {
		List<IObject> llist = new ArrayList<IObject>();
		llist.add(this.buildLineA());
		llist.add(this.buildLineB());
		llist.add(this.buildLineC());
		llist.add(this.buildLineDown());
		return new GroupComponent(0, SPACE, llist.toArray(new IObject[0]));
	}

	private IObject buildLineA() {
		List<IObject> list = new ArrayList<IObject>();
		int x = Register.WIDTH + SPACE;
		/* Linha do Registor até o barramento */
		{
			int x1 = x + SPACE;
			for (int n = 0; n < registers.length; n++) {
				int y = n * (Register.HEIGHT + SPACE) + Register.HEIGHT * 1 / 4;
				list.add(new Line(x, y, x1, y));
				list.add(new Point(x1, y));
				y -= Register.HEIGHT * 1 / 4;
			}
		}
		/* Linha do Registor até a saída do kernel */
		{
			int x1 = this.getWidth() - SPACE;
			for (int n = 0; n < registers.length; n++) {
				int y = n * (Register.HEIGHT + SPACE);
				list.add(new Line(x, y, x1, y));
				list.add(new Point(x1, y));
			}
		}
		/* Linha do Barramento até o Alu */
		{
			int x0 = x + 1 * SPACE;
			int y0 = Register.HEIGHT * 1 / 4;
			int dy0 = Register.HEIGHT * 3 / 4 + SPACE;
			int dy1 = Amux.HEIGHT + SPACE;
			int y1 = y0 + (registers.length - 1) * (Register.HEIGHT + SPACE) + dy0 + dy1;
			list.add(new Line(x0, y0, x0, y1));
		}
		/* Linha do Barramento até a Saída */
		{
			int x0 = x + 1 * SPACE;
			int y0 = Register.HEIGHT;
			int y1 = y0 - INIT_Y - 4 * SPACE;
			list.add(new Line(x0, y0, x0, y1));
			list.add(new Point(x0, y1));
		}
		return new GroupComponent(0, 0, list.toArray(new IObject[0]));
	}

	private IObject buildLineB() {
		List<IObject> list = new ArrayList<IObject>();
		int x = Register.WIDTH + SPACE;
		/* Linha do Registor até o barramento */
		{
			int x1 = x + 2 * SPACE + Amux.DELTA_X / 2;
			for (int n = 0; n < registers.length; n++) {
				int y = n * (Register.HEIGHT + SPACE) + Register.HEIGHT * 3 / 4;
				list.add(new Line(x, y, x1, y));
				list.add(new Point(x1, y));
			}
		}
		/* Linha do Registor até a saída do kernel */
		{
			int x1 = this.getWidth() - SPACE;
			for (int n = 0; n < registers.length; n++) {
				int y = n * (Register.HEIGHT + SPACE) + Register.HEIGHT;
				list.add(new Line(x, y, x1, y));
				list.add(new Point(x1, y));
			}
		}
		/* Linha do Barramento até o Amux */
		{
			int x0 = x + 2 * SPACE + Amux.DELTA_X / 2;
			int y0 = Register.HEIGHT * 3 / 4;
			int dy = Register.HEIGHT * 1 / 4 + SPACE;
			int y1 = y0 + (registers.length - 1) * (Register.HEIGHT + SPACE) + dy;
			list.add(new Line(x0, y0, x0, y1));
		}
		/* Linha do Barramento até a Saída */
		{
			int x0 = x + 2 * SPACE + Amux.DELTA_X / 2;
			int y0 = Register.HEIGHT;
			int y1 = y0 - INIT_Y - 4 * SPACE;
			list.add(new Line(x0, y0, x0, y1));
			list.add(new Point(x0, y1));
		}
		return new GroupComponent(0, 0, list.toArray(new IObject[0]));
	}

	private IObject buildLineC() {
		List<IObject> list = new ArrayList<IObject>();
		/* Linha do Registor até o barramento */
		{
			for (int n = 0; n < registers.length - REGISTER_CONST; n++) {
				int y = n * (Register.HEIGHT + SPACE) + Register.HEIGHT / 2;
				list.add(new Line(0, y, SPACE, y));
				list.add(new Point(0, y));
			}
		}
		/* Linha do Registor até a saída do kernel */
		{
			for (int n = 0; n < registers.length - REGISTER_CONST; n++) {
				int y = n * (Register.HEIGHT + SPACE);
				int x = -(SPACE + INIT_X);
				list.add(new Line(x, y, SPACE, y));
				list.add(new Point(x, y));
			}
		}
		return new GroupComponent(0, 0, list.toArray(new IObject[0]));
	}

	private IObject buildLineDown() {
		List<IObject> list = new ArrayList<IObject>();
		/* Linha que liga o Amux com a Alu */
		{
			int y = (Register.HEIGHT + SPACE) * registers.length + Amux.HEIGHT;
			int y1 = y + SPACE;
			int x = 1 * SPACE + Alu.WIDTH - Amux.WIDTH / 2;
			list.add(new Line(x, y, x, y1));
		}
		{
			int y = (Register.HEIGHT + SPACE) * registers.length + 1 * SPACE + Amux.HEIGHT + Alu.HEIGHT;
			int x = 1 * SPACE + Alu.WIDTH / 2;
			int y1 = y + SPACE;
			/* Linha que liga o Alu com o Shift */
			list.add(new Line(x, y, x, y1));
			int sy = Shift.HEIGHT + SPACE;
			/* Linha do Shift */
			list.add(new Line(x, y + sy, x, y1 + sy));
			/* Linha que liga o barramento C ao Shift */
			list.add(new Line(0, y1 + sy, x, y1 + sy));
			/* Linha do barramento C */
			list.add(new Line(0, Register.HEIGHT / 2, 0, y1 + sy));
		}
		/* Linha de saída do componente */
		{
			/* Linhas da saída da Amux */
			{
				int y = (Register.HEIGHT + SPACE) * registers.length + Amux.DELTA_Y / 2;
				int x0 = Alu.WIDTH + SPACE;
				int x1 = x0 + 1 * SPACE + INIT_X;
				for (int n = 0; n < Amux.IN_COUNT; n++) {
					/* Linha do Amux até a saída do componente */
					list.add(new Line(x0, y, x1, y));
					/* Ponto da saída da Amux */
					list.add(new Point(x1, y));
					y += Amux.DELTA_Y;
				}
			}
			/* Linhas de entrada da Amux */
			{
				int y0 = -(INIT_Y + 2 * SPACE);
				int y1 = (Register.HEIGHT + SPACE) * registers.length;
				int x0 = Alu.WIDTH + SPACE - Amux.WIDTH + (int) (1.5 * Amux.DELTA_X);
				for (int n = 0; n < Amux.OUT_COUNT - 1; n++) {
					list.add(new Line(x0, y0, x0, y1));
					list.add(new Point(x0, y0));
					x0 += Amux.DELTA_X;
				}
			}
			/* Linhas da saída do Alu */
			{
				int y = (Register.HEIGHT + SPACE) * registers.length + 1 * SPACE + Amux.HEIGHT + Alu.DELTA_Y / 2;
				int x0 = Alu.WIDTH + SPACE;
				int x1 = x0 + 1 * SPACE + INIT_X;
				for (int n = 0; n < Alu.IN_COUNT; n++) {
					/* Linha do Amux até a saída do componente */
					list.add(new Line(x0, y, x1, y));
					/* Ponto da saída da Amux */
					list.add(new Point(x1, y));
					y += Alu.DELTA_Y;
				}
			}
			/* Linhas da saída do Shift */
			{
				int y = (Register.HEIGHT + SPACE) * registers.length + 2 * SPACE + Amux.HEIGHT + Alu.HEIGHT + Shift.DELTA_Y / 2;
				int x0 = SPACE + Alu.WIDTH / 2 + Shift.WIDTH / 2;
				int x1 = 2 * SPACE + INIT_X + Alu.WIDTH;
				for (int n = 0; n < Shift.IN_COUNT; n++) {
					/* Linha do Amux até a saída do componente */
					list.add(new Line(x0, y, x1, y));
					/* Ponto da saída da Amux */
					list.add(new Point(x1, y));
					y += Shift.DELTA_Y;
				}
			}
		}
		return new GroupComponent(0, 0, list.toArray(new IObject[0]));
	}

	private IObject buildComponents() {
		List<IObject> llist = new ArrayList<IObject>();
		{
			List<IObject> list = new ArrayList<IObject>();
			for (int n = 0; n < registers.length; n++) {
				list.add(new Register(0, (Register.HEIGHT + SPACE) * n, registers[n]));
			}
			llist.add(new GroupComponent(0, 0, list.toArray(new IObject[0])));
		}
		{
			List<IObject> list = new ArrayList<IObject>();
			list.add(this.amux = new Amux(Register.WIDTH + 2 * SPACE, 0));
			list.add(this.alu = new Alu(0, Amux.HEIGHT + SPACE));
			list.add(this.shift = new Shift(Alu.WIDTH / 2 - Shift.WIDTH / 2, Alu.HEIGHT + Amux.HEIGHT + 2 * SPACE));
			llist.add(new GroupComponent(0, (Register.HEIGHT + SPACE) * registers.length, list.toArray(new IObject[0])));
		}
		return new GroupComponent(SPACE, SPACE, llist.toArray(new IObject[0]));
	}

	@Override
	public int getHeight() {
		return registers.length * (Register.HEIGHT + SPACE) + Amux.HEIGHT + Alu.HEIGHT + Shift.HEIGHT + 5 * SPACE + 2 * INIT_Y;
	}

	@Override
	public int getWidth() {
		return 2 * INIT_X + Alu.WIDTH + 1 * SPACE;
	}

	@Override
	public List<IObject> getObjects() {
		return this.objects;
	}

}
