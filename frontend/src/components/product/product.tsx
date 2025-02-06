interface ProductProps {
    thumbnail: string | undefined
    name: string
    productPrice: number
}

interface DeliveryProps extends ProductProps {
    deliveryPrice: number
    priceType: string
}

export default function Product({
    thumbnail,
    name,
    productPrice,
    priceType,
    deliveryPrice,
}: DeliveryProps) {
    return (
        <>
            <h2>{thumbnail ? thumbnail : undefined}</h2>
            <h2>{name}</h2>
            <h2>{productPrice}</h2>
            <h2>{priceType}</h2>
            <h2>{deliveryPrice}</h2>
        </>
    )
}
