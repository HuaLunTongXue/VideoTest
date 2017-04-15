package kevin.android.om.videotest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;

import kevin.android.om.videotest.R;

import static android.content.ContentValues.TAG;


public class AdvertFragment extends Fragment implements PLMediaPlayer.OnCompletionListener,
                                                        PLMediaPlayer.OnErrorListener{


    private static final String PATH[] = {"/sdcard/Android/test1","/sdcard/Android/test2"};
    private int nextVideo = 0;
    private static final String TAG = AdvertFragment.class.getSimpleName();
    private View mView;
    private PLVideoView mPLVideoView;
    private ImageView imageCover;

    public AdvertFragment() {
        // Required empty public constructor
    }

    public static AdvertFragment newInstance(String param1, String param2) {
        AdvertFragment fragment = new AdvertFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_advert, container, false);
        mPLVideoView = (PLVideoView)mView.findViewById(R.id.advert_video);
        imageCover = (ImageView)mView.findViewById(R.id.imageCover);
        mPLVideoView.setVisibility(View.VISIBLE);
        imageCover.setVisibility(View.INVISIBLE);


        mPLVideoView.setVideoPath(PATH[0]);
        mPLVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        mPLVideoView.start();
        mPLVideoView.setOnCompletionListener(this);
        mPLVideoView.setOnErrorListener(this);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onResume() {
        super.onResume();
        mPLVideoView.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPLVideoView.stopPlayback();
    }


    @Override
    public void onPause() {
        super.onPause();
        mPLVideoView.pause();
    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {
        Log.v(TAG, "onCompletion");
        nextVideo++;
        if(nextVideo == 2){
            nextVideo = 0;
        }
        mPLVideoView.setVideoPath(PATH[nextVideo]);
        mPLVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        mPLVideoView.start();
    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
        Log.v(TAG, "onError, 监听播放器的错误消息: " + i);
//该对象用于监听播放器的错误消息，一旦播放过程中产生任何错误信息，SDK 都会回调该接口，
        //返回值决定了该错误是否已经被处理，如果返回 false，则代表没有被处理，下一步则会触发 onCompletion 消息。
        switch (i) {
            case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                Log.v(TAG, "onError:Invalid URL ! -2 无效的 URL");
                break;
            case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                Log.v(TAG, "onError:Network IO Error ! -5 网络异常");
                break;
            case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                Log.v(TAG, "onError:Stream disconnected ! -11 与服务器连接断开");
                break;
            case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                Log.v(TAG, "onError:Empty playlist ! -541478725 空的播放列表 ");
                break;
            case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                Log.v(TAG, "onError:404 resource not found ! -875574520 播放资源不存在");
                break;
            case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                Log.v(TAG, "onError:Connection refused ! -111 服务器拒绝连接");
                break;
            case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                Log.v(TAG, "onError:Connection timeout ! -110 连接超时");
                break;
            case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                Log.v(TAG, "onError:Unauthorized Error ! -825242872 未授权，播放一个禁播的流");
                break;
            case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                Log.v(TAG, "onError:Prepare timeout ! -2001 播放器准备超时 ");
                break;
            case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                Log.v(TAG, "onError:Read frame timeout ! -2002 读取数据超时 ");
                break;
            case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
            default:
                Log.v(TAG, "onError:unknown error ! -1 未知错误");
                break;
        }
        return true;
    }
}
