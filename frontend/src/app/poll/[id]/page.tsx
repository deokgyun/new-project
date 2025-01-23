export default async function Page({ params }: { params: Promise<{ id: string }> }) {
    const { id } = await params

    return <div>vote/{id} 페이지입니다.</div>
}
