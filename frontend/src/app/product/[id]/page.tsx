'use client'
import { useParams } from 'next/navigation'
import { api } from '@/utils/api'

export default function GetProduct(props: { id: string }) {
    let s = useParams()

    return <h2>상세 페이지 {s.id}</h2>
}
