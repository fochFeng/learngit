好好学习，天天向上。
git init / add ./ commit -m "" / status / diff / stash
git log --pretty==oneline  reflog[用来记录你的每一次提交命令]

回退
没有add  没有commit【工作区】                   git checkout -- file
有add 没有commit【暂存区】                      git reset HEAD file
有add 有commit  【仓库】                        git reset --hard HEAD^
git reset命令即可以回退版本，也可以把暂存区的修改回退到工作区。