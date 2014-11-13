/**
 * 
 */
package inventarium.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import inventarium.data.DataRequest;
import inventarium.entity.Category;
import inventarium.entity.Product;
import inventarium.entity.Vendor;
import inventarium.helper.EntityStatus;
import inventarium.helper.RequestContext;

/**
 * @author mhoffman
 * Implementation of the FindAction class.
 * This class facilitates a system request
 * to find an entity.
 */

public class FindActionImpl implements FindAction {

	RequestContext request;
	EntityStatus entityStatus;
	String searchParams;
	
	FindActionImpl(RequestContext request, EntityStatus entityStatus){
		this.request = request;
		this.entityStatus = entityStatus;
	}
	
	@Override
	public Product findEntity(Product product) {
		Product selectedProduct = null;
		Set<Product> results = new HashSet<Product>();
		List<String> searchTerms = new ArrayList<String>();
		if(product.getUniqueId() != null){
			searchTerms.add("id");
		}
		if(product.getName() != null){
			searchTerms.add("name");
		}
		if(product.getVendor() != null){
			searchTerms.add("vendor");
		}
		if(product.getCategory() != null){
			searchTerms.add("category");
		}
		if(searchTerms.isEmpty()){
			//throw an error
		}else{
			try{
				results = DataRequest.search(product, searchTerms);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(results == null){
			// notify no results found
		}else{
			// generate list view
			// get selection
			// selectedProduct = selection
		}
		return selectedProduct;
	}

	@Override
	public Vendor findEntity(Vendor vendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findEntity(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
