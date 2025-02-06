import ProductList from '@/components/product/product-list'
import { api } from '@/utils/api'

export default async function Page() {
    const res = await api.get('/product')

    const content = res.content

    return (
        <main className="w-full">
            <div>
                <b>상품</b>
                <ProductList products={content} />
            </div>
        </main>
    )
}
