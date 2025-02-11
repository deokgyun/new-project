'use server'
import { ResponseCookie } from 'next/dist/compiled/@edge-runtime/cookies'
import { cookies } from 'next/headers'

export async function setCookie(key: string, value: string, options?: Partial<ResponseCookie>) {
    const cookie = await cookies()
    return cookie.set(key, value, options)
}
