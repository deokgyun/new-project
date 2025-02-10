'use client'

import Link from 'next/link'
import { Card, CardContent, CardFooter } from '@/components/ui/card'
import { Skeleton } from '@/components/ui/skeleton'
import { Pagination, PaginationContent, PaginationItem, PaginationLink } from '@ui'
import { useEffect, useState } from 'react'
import { api } from '@/utils/api'

const BASE_URL: string = `${process.env.NEXT_PUBLIC_API_URL}`

interface ProductProps {
    productId: number
    thumbnail?: string
    name: string
    price: number
    stock: number
}

interface PageProps {
    pageNumber: number
    size: number
    totalElements: number
    totalPages: number
}

type Props = {
    initialProducts: ProductProps[]
    page: PageProps
}

export default function ProductList({ initialProducts, page }: Props) {
    const pages = []

    const [products, setProducts] = useState(initialProducts)
    const [currentPage, setCurrentPage] = useState<number>(0)

    const loadPage = async (pageNumber: number) => {
        const restPage = pageNumber - 1
        const res = await api.get(`/api/product?page=${restPage}&q=`)
        const content = res.content

        setProducts(content)
        setCurrentPage(restPage)
    }

    useEffect(() => {
        console.log('curr === ' + currentPage)
    }, [])

    for (let i: number = 1; i <= page.totalPages; i++) {
        pages.push(
            <PaginationLink
                key={i}
                href={''}
                onClick={() => loadPage(i)}
                isActive={currentPage + 1 === i}>
                {i}
            </PaginationLink>,
        )
    }

    return (
        <div>
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
                                        {product.price
                                            .toString()
                                            .replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                                    </p>
                                </Skeleton>
                            </CardContent>
                            <CardFooter className="flex justify-between"></CardFooter>
                        </Card>
                    </Link>
                ))}
            </div>
            <div>
                <Pagination>
                    <PaginationContent>
                        <PaginationItem>{pages}</PaginationItem>
                    </PaginationContent>
                </Pagination>
            </div>
        </div>
    )
}
