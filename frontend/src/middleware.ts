import { type NextRequest, NextResponse } from 'next/server'

export default function middleware(request: NextRequest) {}

export const config = {
    matcher: ['/signup', '/poll/:path*'],
}
