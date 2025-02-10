import { Pagination, PaginationContent, PaginationItem, PaginationLink } from '../ui/pagination'

const BASE_URL: string = `${process.env.NEXT_PUBLIC_API_URL}`

interface PageProps {
    pageNumber: number
    size: number
    totalElements: number
    totalPages: number
}

export default function Paging({ page, ...props }: { page: PageProps }) {
    const pages = []

    for (let i = 1; i <= page.totalPages; i++) {
        pages.push(
            <PaginationItem>
                <PaginationLink key={i} href={`${BASE_URL}/product?page=${i - 1}`}>
                    {i}
                </PaginationLink>
            </PaginationItem>,
        )
    }
    return (
        <Pagination>
            <PaginationContent>{pages}</PaginationContent>
        </Pagination>
    )
}
