import { useEffect, useState } from "react"
import { ProductService } from "../services/ProductService";
import ProductCard from "./ProductCard";
import { Link } from "react-router-dom";
import './ProductList.scss';
export const ProductList = () => {
    const [products, setProducts] = useState([])
    useEffect(()=>{
        const fetchProducts = async () => {
            try {
                const res = await ProductService.getAllProducts();
                const data = await res.data;
                setProducts(data);
            } catch(error) {
                alert(error);
            }
        }
        fetchProducts();
    }, []);
    return (
      <div className="product-list">
        {
            products.map((item, index) => {
                return(
                    <Link key={index} to={`/product/${item.id}`} ><ProductCard key={index} product={item}/></Link>
                ) 
            } )
        }
      </div>
    )
}