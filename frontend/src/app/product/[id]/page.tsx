import { api } from '@/utils/api'
import { Button } from '@ui'

export default async function GetProduct({ params }: any): Promise<any> {
    const { id } = await params

    const data: Product = await api.get(`/api/product/${id}`)

    return (
        <div className="text-xl">
            <p>상세 페이지</p>
            <p>{data.name}</p>
            <p>가격 : {data.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원</p>
            <p>{data.description}</p>
            <p>재고 : {data.stock.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}개</p>
            <p>{data.deliveryCompany}</p>
            <p>{data.deliveryPriceType}</p>
            <p>배송비 : {data.deliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원</p>
            <Button>결제하기</Button>
        </div>
    )
}
