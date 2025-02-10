'use client'

import { Button, Card, Checkbox, Input, Label, Switch } from '@ui'
import React, { useEffect, useState } from 'react'
import { api } from '@/utils/api'
import Link from 'next/link'
import { useRouter } from 'next/navigation'

export default function LoginPage() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const router = useRouter()

    const handleLogin = async (e) => {
        e.preventDefault()

        const data = {
            email: email,
            password: password,
        }

        try {
            const response = await api.post('/login', {
                email: data.email,
                password: data.password,
            })

            localStorage.setItem('access', response.body.access)
            localStorage.setItem('refresh', response.body.refresh)
            setIsLoggedIn(true)
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(() => {
        if (isLoggedIn) {
            const previousPage: string = window.sessionStorage.getItem('prev')
            router.push(previousPage)
        }
    }, [isLoggedIn, router])

    return (
        <main className="flex justify-center items-center mt-24">
            <Card.Card className="w-[30rem]">
                <Card.CardHeader>
                    <Card.CardTitle>로그인</Card.CardTitle>
                </Card.CardHeader>
                <form onSubmit={handleLogin}>
                    <Card.CardContent className="flex flex-col gap-y-2 pb-4">
                        <Input
                            id="email"
                            type="email"
                            value={email}
                            variant="myInput"
                            placeholder="이메일을 입력하세요."
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <Input
                            id="password"
                            type="password"
                            value={password}
                            variant="myInput"
                            placeholder="비밀번호를 입력하세요."
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Card.CardContent>
                    <Card.CardFooter className="block">
                        <div className="w-full flex justify-between mb-2">
                            <div className="flex items-center space-x-2">
                                <Checkbox id="keep" />
                                <Label htmlFor="keep">로그인 유지하기</Label>
                            </div>
                            <div className="flex items-center space-x-2">
                                <Label htmlFor="id-security">IP 보안</Label>
                                <Switch id="ip-security" />
                            </div>
                        </div>
                        <Button className="w-full" type={'submit'}>
                            로그인
                        </Button>
                        <Link href={'/signup'} className="text-gray-700">
                            회원가입
                        </Link>
                    </Card.CardFooter>
                </form>
            </Card.Card>
        </main>
    )
}
