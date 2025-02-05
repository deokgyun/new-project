'use client'

import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from '@/components/ui/form'
import { Button, Card, Input } from '@ui'
import React from 'react'
import { useForm } from 'react-hook-form'
import { z } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import { toast } from '@/hooks/use-toast'

const FormSchema = z.object({
    email: z.string().min(2, {
        message: 'Username must be at least 2 characters.',
    }),
    password: z.string(),
    name: z.string(),
    phoneNumber: z.string(),
    birthday: z.string(),
})

export default function Page() {
    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {
            email: '',
            password: '',
            name: '',
            phoneNumber: '',
            birthday: '',
        },
    })

    function onSubmit(data: z.infer<typeof FormSchema>) {
        toast({
            title: 'You submitted the following values:',
            description: (
                <pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
                    <code className="text-white">{JSON.stringify(data, null, 2)}</code>
                </pre>
            ),
        })
    }

    return (
        <main className="w-full mt-24">
            <Card.Card>
                <Card.CardHeader>
                    <Card.CardTitle>회원가입</Card.CardTitle>
                </Card.CardHeader>
                <Card.CardContent className="flex flex-col gap-y-2 pb-4">
                    <Form {...form}>
                        <form onSubmit={form.handleSubmit(onSubmit)} className="w-full space-y-4">
                            <FormField
                                control={form.control}
                                name="email"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>이메일</FormLabel>
                                        <FormControl>
                                            <Input placeholder="이메일을 입력하세요." {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="password"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>비밀번호</FormLabel>
                                        <FormControl>
                                            <Input
                                                type="password"
                                                placeholder="비밀번호를 입력하세요."
                                                {...field}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <Card.CardFooter className="block">
                                <Button className="w-full" type="submit">
                                    Submit
                                </Button>
                            </Card.CardFooter>
                        </form>
                    </Form>
                </Card.CardContent>
            </Card.Card>
        </main>
    )
}
