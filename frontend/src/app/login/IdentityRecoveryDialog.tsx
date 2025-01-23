import { Button, Dialog, Input } from '@ui'
import { useState } from 'react'

export default function IdentityRecoveryDialog() {
    const [open, setOpen] = useState<boolean>(false)

    return (
        <Dialog.Dialog open={open} onOpenChange={setOpen}>
            <Dialog.DialogTrigger asChild>
                <Button variant="link">비밀번호를 잊어버리셨나요?</Button>
            </Dialog.DialogTrigger>
            <Dialog.DialogContent>
                <Dialog.DialogHeader>
                    <Dialog.DialogTitle>비밀번호 재설정</Dialog.DialogTitle>
                    <Dialog.DialogDescription>
                        회원가입시 기입한 이메일로 비밀번호 재설정 링크를 보내 드립니다.
                    </Dialog.DialogDescription>
                </Dialog.DialogHeader>
                <div className="flex flex-col space-y-2">
                    <Input variant="myInput" placeholder="아이디를 입력하세요." />
                    <Input
                        variant="myInput"
                        placeholder="가입시 사용한 이메일 주소를 입력하세요."
                    />
                </div>
                <Dialog.DialogFooter>
                    <Button className="w-full" onClick={() => setOpen(false)}>
                        비밀번호 재설정
                    </Button>
                </Dialog.DialogFooter>
            </Dialog.DialogContent>
        </Dialog.Dialog>
    )
}
