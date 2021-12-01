package listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class ShareniuTaskListener implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2336611537831911625L;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		delegateTask.setAssignee("efgh");
	}



}
