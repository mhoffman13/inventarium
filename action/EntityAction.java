package inventarium.action;

import inventarium.entity.Category;
import inventarium.entity.Product;
import inventarium.entity.Vendor;

/**
 * @author mhoffman
 * EntityAction outlines the methods to be
 * defined in EntityActionImpl
 */

public interface EntityAction {
	public boolean addEditEntity(Product product);
	public boolean addEditEntity(Vendor vendor);
	public boolean addEditEntity(Category category);
}