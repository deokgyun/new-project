'use client'

import React from 'react'
import { Button, Card, Checkbox, Input, Label, Switch } from '@ui'
import Link from 'next/link'
import { handleLogin } from './authActios'

export default function LoginForm() {
    return (
        <main className="flex justify-center items-center mt-24">
            <Card.Card className="w-[30rem]">
                <Card.CardHeader>
                    <Card.CardTitle>로그인</Card.CardTitle>
                </Card.CardHeader>
                <form action={handleLogin}>
                    <Card.CardContent className="flex flex-col gap-y-2 pb-4">
                        <Input
                            name="email"
                            type="email"
                            variant="myInput"
                            placeholder="이메일을 입력하세요."
                        />
                        <Input
                            name="password"
                            type="password"
                            variant="myInput"
                            placeholder="비밀번호를 입력하세요."
                        />
                    </Card.CardContent>
                    <Card.CardFooter className="block">
                        <div className="w-full flex justify-between mb-2">
                            <div className="flex items-center space-x-2">
                                <Checkbox id="keep" />
                                <Label htmlFor="keep">로그인 유지하기</Label>
                            </div>
                            <div className="flex items-center space-x-2">
                                <Label htmlFor="ip-security">IP 보안</Label>
                                <Switch id="ip-security" />
                            </div>
                        </div>
                        <Button className="w-full" type="submit">
                            로그인
                        </Button>
                        <Link href="/signup" className="text-gray-700">
                            회원가입
                        </Link>
                    </Card.CardFooter>
                </form>
            </Card.Card>
        </main>
    )
}
