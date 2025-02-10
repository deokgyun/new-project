import { type NextRequest, NextResponse } from 'next/server'

export default function middleware(request: NextRequest) {
    console.log('middle === ' + request)
}

export const config = {
    matcher: ['/signup', '/product/:path*'],
}
