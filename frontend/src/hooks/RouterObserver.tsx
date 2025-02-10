'use client'

import { usePathname } from 'next/navigation'
import { useEffect, useRef } from 'react'

export default function RouteChangeObserver() {
    const pathName = usePathname()
    const previousPage = useRef<string | null>(null) //단순 저장을 위해서 useRef 사용
    useEffect(() => {
        if (pathName === '/login' && previousPage.current) {
            window.sessionStorage.setItem('prev', previousPage.current)
            return
        }
        previousPage.current = pathName
    }, [pathName])
    return <></>
}
