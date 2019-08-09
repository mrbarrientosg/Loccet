package delegate;

import cell.FilterCell;

/**
 * Delegate cuando se elimina un filtro
 */
public interface FilterCellDelegate {
    public void onDelete(FilterCell cell);
}
