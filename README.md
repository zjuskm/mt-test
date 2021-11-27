## 解决思路
1. 考虑到单店服务数据量不大，且业务场景需要保证先到先服务，因此，配置两个队列（任务队列Queue<MassageClient>和工作队列MassageWorkerGroup），工作队列长度可遍历取最优解
2. 技师的最大并发数是客人同时等待的上限

## 入参调整
为方便处理，入参到达时间14:30格式统一调整为14*60+30=870，距离0点时分的分钟数来表示当前时间。

## 测试用例
com.mt.test.service.IMassageServiceTest.decideWorkers
请定位到该文件，执行相应测试用例

