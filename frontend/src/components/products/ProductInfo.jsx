import { useEffect, useState } from "react";
import { ProductService } from "../services/ProductService";
import { useParams } from "react-router-dom";
import './ProductInfo.scss';
import StarRatings from 'react-star-ratings';

export const ProductInfo = () => {

  const [product, setProduct] = useState({});
  const { id } = useParams();
  const [rating, setRating] = useState(0);

  useEffect(() => {
    const fetchProduct = async () => {
      const res = await ProductService.getProductById(id);
      console.log(res);
      const data = await res.data;
      setProduct(data);
      setRating(Number(data.rating.rate));
    }
    fetchProduct();
  }, [])
  return (
    <div className="product-info">
      <div className="product-info-image">
        <img src={product.image} alt={product.title} />
      </div>
      <div className="product-info-details">
        <div className="product-info-name">
          {product.title}
        </div>
        <StarRatings
          numberOfStars={5}
          rating={rating}
          starRatedColor={rating>3?'#005F2D':rating<3&&rating>1.5?'#FFFF00':'#FF0000'}
          starDimension='35px'
          starSpacing='2px'
        />
        <div>
          {product.category}
        </div>
        <div className="product-info-price">
          Rs. {product.price}
        </div>
      </div>
      <div className="product-info-description">
        {product.description}
      </div>

    </div>
  )
}