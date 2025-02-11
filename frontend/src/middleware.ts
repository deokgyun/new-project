import { type NextRequest, NextResponse } from 'next/server'

export function middleware(request: NextRequest) {
    // if (!access || !refresh) {
    //     return NextResponse.redirect(new URL('/login', request.url))
    // }
}

export const config = {
    matcher: ['/((?!api|_next/static|_next/image|favicon.ico|fonts|images).*)'],
}
