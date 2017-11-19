function Jordan(S)
N = S(3 : 5 , 3 : 5)
m = poly(N)
for i = 1 : 5
roots(m)
m = m + 10^(-3i);
endfor
endfunction