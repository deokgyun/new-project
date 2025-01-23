import Link from 'next/link'

export default function Header() {
    return (
        <header className="flex justify-between items-center py-4">
            <div className="flex items-center">
                <div className="mr-8">
                    <Link href={'/'}>
                        <img src="/logo.png" alt="Logo" className="h-8" />
                    </Link>
                </div>
                <nav className="flex space-x-4">
                    <Link href={'/poll/1'} className="text-gray-700">
                        투표
                    </Link>
                </nav>
            </div>
            <div>
                <div className="flex space-x-4">
                    <Link href={'/login'} className="text-gray-700">
                        로그인 / 회원가입
                    </Link>
                </div>
            </div>
        </header>
    )
}
