
# @author: Parsa P. Esfahani
	
.data

arrayA: .float 0.05, 1.12, 2.26, 3.31, 4.45, 5.55, 6.65, 7.75, 8.85, 9.95
arrayB: .float 10.04, 20.14, 30.24, 40.44, 50.54, 60.64, 70.74, 80.84, 90.94, 100.04
arrayC:	.float 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00
length:	.word 10
result:	.asciiz "Array C: ["
space: .asciiz ", "
closeBracket: .asciiz "]"
	
.text

.globl main

main:
	la $t0, arrayA
	la $t1, arrayB
	la $t2, arrayC
	la $t4, length
	li $t5, 0
	lw $t6, 0($t4)

	li $v0, 4
	la $a0, result
	syscall

loop: 	lwc1 $f0, 0($t0)
	lwc1 $f1, 0($t1)
	add.s $f2, $f0, $f1
	swc1 $f2, 0($t2)

	li $v0, 2
	mov.s $f12, $f2
	syscall	

	addi $t0, $t0, 4
	addi $t1, $t1, 4
	addi $t2, $t2, 4
	addi $t5, $t5, 1

	beq $t6, $t5, Format
	
	li $v0, 4
	la $a0, space
	syscall
	
Format:	blt $t5, $t6, loop

	li $v0, 4
	la $a0, closeBracket
	syscall
	
	li $v0, 10
	syscall
