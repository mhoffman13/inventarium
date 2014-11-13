package inventarium.action;

import inventarium.entity.Category;
import inventarium.entity.Product;
import inventarium.entity.Vendor;

/**
 * @author mhoffman
 * FindAction outlines the methods to be
 * defined in FindActionImpl
 */

public interface FindAction {
	public Product findEntity(Product product);
	public Vendor findEntity(Vendor vendor);
	public Category findEntity(Category category);
}
