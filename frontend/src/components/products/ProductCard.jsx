import "./ProductCard.scss";
const ProductCard = ({product}) => {
    
    return (
        <div className='card-container'>
            <div className='image-container'>
                <img src={product.image} alt={product.title} />
            </div>
            <div className='card-details'>
                <div className='card-title'>
                    {product.title}
                </div>
            </div>
        </div>
    )
}

export default ProductCard