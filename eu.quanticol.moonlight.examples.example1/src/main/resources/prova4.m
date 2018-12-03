time = 0:1:200;
X = time;
T = time;
trace = @(X,T)[time' X' T'];
stringTrace = {'a','b'};
stringFormulaName = 'phi';
stringFormula = 'alw_[0,500] (a[t]>=4)';
robBreach = @(X,T) robEval(stringTrace, trace(X,T),stringFormulaName,stringFormula);