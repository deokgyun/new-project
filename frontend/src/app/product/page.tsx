import ProductList from '@/components/product/product-list'
import { api } from '@/utils/api'

interface PageProps {
    pageNumber: number
    size: number
    totalElements: number
    totalPages: number
}

export default async function Page() {
    const res = await api.get('/api/product')

    const content = res.content
    const page: PageProps = res.page

    return (
        <main className="w-full">
            <div>
                <b>상품</b>
                <ProductList initialProducts={content} page={page} />
            </div>
        </main>
    )
}
