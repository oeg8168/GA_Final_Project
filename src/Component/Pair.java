package Component;

/**
 * @see <a
 *      href="http://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples">
 *      Reference Link </a>
 */
public class Pair<L, R> {

	private L left;
	private R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return this.left;
	}

	public R getRight() {
		return this.right;
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) {
			return false;
		}

		Pair<?, ?> pair = (Pair<?, ?>) o;

		boolean flag = this.getLeft().equals(pair.getLeft()) && this.getRight().equals(pair.getRight());
		flag |= (this.getLeft().equals(pair.getRight()) && this.getRight().equals(pair.getLeft()));

		return flag;
	} // end of equals()

} // end of class Pair
