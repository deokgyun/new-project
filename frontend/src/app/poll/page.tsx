export default async function Page() {
    interface Member {
        id: number
        email: string
        nickname: string
        password: string
    }

    const response = await fetch(`http://localhost:8080/members`)
    const allMembers = await response.json()
    const { data }: { data: Member[] } = allMembers

    if (!response.ok) {
        return <div>오류 발생</div>
    }

    return <div>{data.map((mem) => mem.id)}</div>
}
