import Link from 'next/link'
import { Button, Input } from '@ui'
import {
    NavigationMenu,
    NavigationMenuItem,
    NavigationMenuLink,
    NavigationMenuList,
} from '@/components/ui/navigation-menu'

export default function Header() {
    return (
        <header className="flex justify-between items-center py-4">
            <div className="logo">
                <div className="mr-8">
                    <Link href={'/'}>
                        <img src="/logo.png" alt="Logo" className="h-16 w-40" />
                    </Link>
                </div>
            </div>
            <div className="flex justify-between">
                <NavigationMenu>
                    <NavigationMenuList>
                        <NavigationMenuItem className="hover:underline">
                            <Link href="/product" legacyBehavior passHref>
                                <NavigationMenuLink>상품</NavigationMenuLink>
                            </Link>
                        </NavigationMenuItem>
                    </NavigationMenuList>
                </NavigationMenu>
            </div>
            <div className="flex items-center space-x-2">
                <Input type="text" placeholder="검색" />
                <Button type="submit">돋보기</Button>
            </div>
            <div>
                <div className="flex space-x-4">
                    <Button className="bg-green-400">
                        <Link href={'/cart'} className="text-gray-700">
                            cart
                        </Link>
                    </Button>
                    <Button className="bg-blue-400">
                        <Link href={'/login'} className="text-gray-700">
                            login/join
                        </Link>
                    </Button>
                </div>
            </div>
        </header>
    )
}
