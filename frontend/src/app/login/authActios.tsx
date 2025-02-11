'use server'

import { api } from '@/utils/api'
import { cookies } from 'next/headers'

export async function handleLogin(formData: FormData) {
    const email = formData.get('email')?.toString()
    const password = formData.get('password')?.toString()
    const cookie = await cookies()

    const response = await api.post('/login', {
        email: email,
        password: password,
    })

    // 쿠키 설정
    cookie.set('access', response.body.access, {
        httpOnly: true,
        secure: true,
        sameSite: 'none',
    })

    cookie.set('refresh', response.body.refresh, {
        httpOnly: true,
        secure: true,
        sameSite: 'none',
    })
}
