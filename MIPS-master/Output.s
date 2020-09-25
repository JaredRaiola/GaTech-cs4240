Briggs:

Liveness:
====================
n_stf_fact_2_0
Start
1
1
End
7
8
[8, 7, 6, 4, 5, 2, 3, 1]
====================
$temp4
Start
5
1
End
2
4
[4, 2, 3, 1]
====================
$temp1
Start
6
1
End
9
1
[1]
====================
$temp2
Start
7
1
End
6
8
[8, 7, 6, 4, 5, 2, 3, 1]
====================
$temp3
Start
8
1
End
2
4
[4, 2, 3, 1]
====================
$temp0
Start
1
5
End
2
8
[8, 7, 6, 4, 5]
====================
$temp5
Start
2
6
End
2
6
[6]
====================
$temp6
Start
3
6
End
3
6
[6]
====================
$temp7
Start
4
6
End
4
6
[6]
====================
r_st_1_0
Start
6
6
End
4
8
[8, 7, 6]
====================
$temp9
Start
7
6
End
7
6
[6]
====================
$temp10
Start
8
6
End
8
6
[6]
====================
$temp11
Start
9
6
End
0
0
[6]
====================

Blocks:
1
[int fact_st_1_0(int n_stf_fact_2_0):, int-list: $temp8, $temp9, $temp10, $temp0, $temp1, $temp2, $temp3, $temp4, $temp11, $temp5, $temp6, $temp7, n_stf_fact_2_0, float-list:, fact_st_1_0:, assign, $temp4, 0, assign, $temp1, n_stf_fact_2_0, assign, $temp2, 1, assign, $temp3, 1, brneq, $temp1, $temp2, cond_0_stz_stf_fact_2_0]
[cond_0_stz_stf_fact_2_0, next]
-----------------------
2
[goto, cond_1_after_stf_fact_2_0]
[cond_1_after_stf_fact_2_0]
-----------------------
3
[cond_0_stz_stf_fact_2_0:, assign, $temp3, 0]
[next]
-----------------------
4
[cond_1_after_stf_fact_2_0:, breq, $temp3, $temp4, if_after2__stf_fact_2_0]
[if_after2__stf_fact_2_0, next]
-----------------------
5
[assign, $temp0, 1, return, $temp0]
[next]
-----------------------
6
[if_after2__stf_fact_2_0:, assign, $temp5, n_stf_fact_2_0, assign, $temp6, 1, sub, $temp5, $temp6, $temp7, callr, $temp8, fact_st_1_0, $temp7, assign, r_st_1_0, $temp8, assign, $temp9, n_stf_fact_2_0, assign, $temp10, r_st_1_0, mult, $temp9, $temp10, $temp11, return, $temp11, , ]
[next]
-----------------------
7
[void main():, int-list: $temp0, $temp1, $temp2, r_st_1_0, float-list:, assign, r_st_1_0, 1]
[next]
-----------------------
8
[main:, assign, $temp0, 5, callr, $temp1, fact_st_1_0, $temp0, assign, r_st_1_0, $temp1, assign, $temp2, r_st_1_0, call, printi, $temp2, return]
[end]
-----------------------

CFG:
Block #1 maps to:
3 2 
Prev Blocks:


Block #2 maps to:
4 
Prev Blocks:
1 

Block #3 maps to:
4 
Prev Blocks:
1 

Block #4 maps to:
6 5 
Prev Blocks:
2 3 

Block #5 maps to:
6 
Prev Blocks:
4 

Block #6 maps to:
7 
Prev Blocks:
4 5 

Block #7 maps to:
8 
Prev Blocks:
6 

Block #8 maps to:
-1 
Prev Blocks:
7 


MIPS:
.data
$s8:  .space 4
$s6:  .space 4
$s5:  .space 4
$s4:  .space 4

.text

fact_st_1_0:
sub $sp, $sp, 68
sw $ra, 16($sp)
sw $a0, 68($sp)
li $s5, 0
li $s0, 1
li $s3, 1
bne $s4, $s2, cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $s3, 0
cond_1_after_stf_fact_2_0:
beq $s5, $s2, if_after2__stf_fact_2_0
li $s0, 1
move $v0, $s1
lw $ra, 16($sp)
addi $sp, $sp, 68
jr $ra
if_after2__stf_fact_2_0:
li $s8, 1
lw $s2, $s8
sub $s8, $s7, $s2
sw $s0, 72($sp)
sw $s1, 76($sp)
sw $s2, 80($sp)
lw $a0, 64($sp)
jal fact_st_1_0
lw $s0, 72($sp)
lw $s1, 76($sp)
lw $s2, 80($sp)
sw $v0, 20($sp)
lw $s1, $s6
sw $s4, 28($sp)
lw $s1, $s5
lw $s2, $s4
mulo $s4, $s4, $s5
sw $s4, 52($sp)
move $v0, $s4
lw $ra, 16($sp)
addi $sp, $sp, 68
jr $ra

main:
sub $sp, $sp, 32
sw $ra, 16($sp)
li $s6, 1
li $s0, 5
sw $s0, 36($sp)
lw $a0, 20($sp)
jal fact_st_1_0
lw $s0, 36($sp)
sw $v0, 24($sp)
lw $s1, $s4
sw $s6, 32($sp)
lw $s1, $s6
sw $s2, 28($sp)
lw $a0, 28($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 32
jr $ra
