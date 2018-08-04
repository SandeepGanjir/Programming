### You need to implement floating point addition, multiplication, and subtraction in ARM assembly.
### The assembly file reads a file called input.txt, and parse its operands. You need to insert your code between the lines (### INSERT YOUR CODE HERE) and (### YOUR CODE ENDS HERE).
### The file input.txt contains a single line with the following format:
###		<FP Operand 1> Operator <FP Operand 2>
### You need to do the appropriate computation, and put the result in register r0 (IEEE 754 format).
###
### The Operator can be one of the following : (+, -, *). Some examples are shown below. (One example in each separate line)
###		2 + 3
###		2e-19 * 3.08e-12
###		-2.34e-18 * -2e12

	.file	"stub.c"
	.section	.rodata.str1.4,"aMS",%progbits,1
	.align	2
.LC0:
	.ascii	"Input 1 = %x \n\000"
	.align	2
.LC1:
	.ascii	"Operator = %c \n\000"
	.align	2
.LC2:
	.ascii	"Input 2 = %x \n\000"
	.text
	.align	2
	.global	process
	.type	process, %function
process:
	@ args = 0, pretend = 0, frame = 0
	@ frame_needed = 1, uses_anonymous_args = 0
	mov	ip, sp
	stmfd	sp!, {r4, r5, fp, ip, lr, pc}
	sub	fp, ip, #4
	and	r4, r1, #255
	mov	r1, r0
	ldr	r0, .L2
	mov	r5, r2
	mov r7, r1 
	bl	printf
	mov	r1, r4
	ldr	r0, .L2+4
	mov r8, r1
	bl	printf
	mov	r1, r5
	mov r9, r1
	ldr	r0, .L2+8
	bl	printf

### INSERT YOUR CODE HERE
	### r7 contains the first operand
	### r8 contains the sign (+, -, *)
	### r9 contains the second operand
	mov	r0, #0x80000000
	mov	r10, r7
	bl	init
	stmfd	sp!, {r1, r3, r5, r10}
	mov	r11, r12
	mov	r10, r9
	bl	init
	cmp	r8, #45
	eoreq	r1, r1, #1
	cmp	r11, r12
	ldmlsfd	sp!, {r2, r4, r6, r12}
	stmhifd	sp!, {r1, r3, r5, r10}
	ldmhifd	sp!, {r2, r4, r6, r12}
	ldmhifd	sp!, {r1, r3, r5, r10}
	cmp	r6, #0
	moveq	r0, r10
	beq	End
	cmp	r5, #255
	moveq	r0, r10
	beq	End
	cmp	r8, #42
	beq	Multiply
	sub	r6, r5, r6
	mov	r11, r4, lsr r6
	cmp	r2, r1
	beq	Add
  Sub:
	sub	r10, r3, r11
	mov	r2, #-1
	clz	r11, r10
	sub	r5, r5, r11
	mov	r10, r10, lsl r11
	sub	r6, r6, r11
	cmp	r5, #1
	movlt	r0, r1, lsl #31
	blt	End
	mov	r10, r10, lsl #1
	b	Rnd
  Add:
	adds	r10, r3, r11
	adc	r5, r5, #0
	mov	r2, #1
	cmp	r6, #0
	movne	r10, r10, lsl #1
  Rnd:
	rsb	r6, r6, #24
	mov	r3, r10, lsr #9
	mov	r4, r4, lsl r6
	b	RnM
  init:
	mov	r1, r10, lsr #31
	mov	r12, r10, lsl #1
	mov	r5, r12, lsr #24
	mov	r3, r10, lsl #9
	add	r3, r0, r3, lsr #1
	mov	pc, lr
  Multiply:
	add	r5, r5, r6
	sub	r5, r5, #127
	eor	r1, r1, r2
	mov	r6, #255
	cmp	r5, r6
	movge	r0, r6, lsl #23
	addge	r0, r0, r1, lsl #31
	bge	End
	cmp	r5, #0
	movle	r0, #0
	ble	End
	mov	r11, r3, lsr #4
	mov	r12, r4, lsr #4
	umull	r4, r3, r12, r11
	mov	r10, r3, lsr #23
	cmp	r10, #0
	moveq	r3, r3, lsl #10
	moveq	r3, r3, lsr #9
	mov	r2, #1
  RnM:
	mov	r10, r4, lsr #31
	mul	r10, r2, r10
	mov	r4, r4, lsl #1
	and	r12, r3, #1
	cmp	r4, #0
	muleq	r10, r12, r10
	add	r0, r3, r10
	add	r0, r0, r5, lsl #23
	orr	r0, r0, r1, lsl #31
 End:
	@@@ *(42),+(43),-(45)
	### Put the final output in r0
### YOUR CODE ENDS HERE

	ldmfd	sp, {r4, r5, fp, sp, pc}
.L3:
	.align	2
.L2:
	.word	.LC0
	.word	.LC1
	.word	.LC2
	.size	process, .-process
	.section	.rodata
	.align	2
	.type	filename.0, %object
	.size	filename.0, 9
filename.0:
	.ascii	"input.txt\000"
	.global	__extendsfdf2
	.section	.rodata.str1.4
	.align	2
.LC3:
	.ascii	"r\000"
	.align	2
.LC4:
	.ascii	"%f %c %f\000"
	.align	2
.LC6:
	.ascii	"Result = %f (float) , %x (hex)\n\000"
	.align	2
.LC5:
	.ascii	"File Not Found \000"
	.text
	.align	2
	.global	main
	.type	main, %function
main:
	@ args = 0, pretend = 0, frame = 140
	@ frame_needed = 1, uses_anonymous_args = 0
	mov	ip, sp
	stmfd	sp!, {r4, r5, fp, ip, lr, pc}
	sub	fp, ip, #4
	sub	sp, sp, #144
	ldr	r0, .L12
	ldr	r1, .L12+4
	bl	fopen
	subs	r5, r0, #0
	subne	r4, fp, #152
	bne	.L6
	b	.L11
.L8:
	sub	ip, fp, #160
	str	ip, [sp, #0]
	bl	sscanf
.L6:
	mov	r1, #128
	mov	r2, r5
	mov	r0, r4
	bl	fgets
	cmp	r0, #0
	ldr	r1, .L12+8
	sub	r2, fp, #156
	sub	r3, fp, #21
	mov	r0, r4
	bne	.L8
	mov	r0, r5
	bl	fclose
.L9:
	ldr	r2, [fp, #-160]
	ldrb	r1, [fp, #-21]	@ zero_extendqisi2
	ldr	r0, [fp, #-156]
	bl	process
	mov	r4, r0
	bl	__extendsfdf2
	mov	r3, r4
	mov	r2, r1
	mov	r1, r0
	ldr	r0, .L12+12
	bl	printf
	sub	sp, fp, #20
	ldmfd	sp, {r4, r5, fp, sp, pc}
.L11:
	ldr	r0, .L12+16
	bl	puts
	b	.L9
.L13:
	.align	2
.L12:
	.word	filename.0
	.word	.LC3
	.word	.LC4
	.word	.LC6
	.word	.LC5
	.size	main, .-main
	.ident	"GCC: (GNU) 3.4.3"
