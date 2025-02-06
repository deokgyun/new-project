import Link from 'next/link'
import { Card, CardContent, CardFooter } from '@/components/ui/card'
import { Skeleton } from '@/components/ui/skeleton'

interface ProductProps {
    productId: number
    thumbnail?: string
    name: string
    price: number
    stock: number
}

type Props = {
    products: ProductProps[]
}

export default function ProductList({ products }: Props) {
    return (
        <div className="grid grid-cols-4 gap-4">
            {products.map((product: ProductProps) => (
                <Link href={`/product/${product.productId}`} key={product.productId}>
                    <Card key={product.productId} className="w-full border-none">
                        <CardContent className="flex flex-col space-y-3 mt-4">
                            <Skeleton className="h-[125px] w-[250px] rounded-xl bg-gray-200 text-center">
                                Image
                            </Skeleton>
                            <Skeleton className="h-[30px] w-[250px] rounded-xl bg-gray-200 text-center">
                                <p className="text-xl">{product.name}</p>
                            </Skeleton>
                            <Skeleton className="h-[30px] w-[250px] rounded-xl text-center">
                                <p className="text-xl">
                                    {product.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                                </p>
                            </Skeleton>
                        </CardContent>
                        <CardFooter className="flex justify-between"></CardFooter>
                    </Card>
                </Link>
            ))}
        </div>
    )
}
